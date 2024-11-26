//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.productdemoaop.dao;

import cn.edu.xmu.javaee.productdemoaop.dao.bo.OnSale;
import cn.edu.xmu.javaee.productdemoaop.jpa.Entity.OnSalePoEntity;
import cn.edu.xmu.javaee.productdemoaop.jpa.Repository.OnSaleRepository;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.OnSalePoMapper;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.OnSalePo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.OnSalePoExample;
import cn.edu.xmu.javaee.productdemoaop.util.CloneFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Repository
public class OnSaleDao {

    private final static Logger logger = LoggerFactory.getLogger(OnSaleDao.class);

    private OnSalePoMapper onSalePoMapper;

    @Resource
    private OnSaleRepository onSaleRepository;
    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    public OnSaleDao(OnSalePoMapper onSalePoMapper) {
        this.onSalePoMapper = onSalePoMapper;
    }

    /**
     * 获得货品的最近的价格和库存
     * @param productId 货品对象
     * @return 规格对象
     */
    public List<OnSale> getLatestOnSale(Long productId) throws DataAccessException {
        LocalDateTime now = LocalDateTime.now();
        OnSalePoExample example = new OnSalePoExample();
        example.setOrderByClause("end_time DESC");
        OnSalePoExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        criteria.andBeginTimeLessThanOrEqualTo(now);
        criteria.andEndTimeGreaterThanOrEqualTo(now);
        List<OnSalePo> onsalePoList = onSalePoMapper.selectByExample(example);
        return onsalePoList.stream().map(po-> CloneFactory.copy(new OnSale(), po)).collect(Collectors.toList());
    }

    public List<OnSale> getLatestOnSale_Jpa(Long productId) throws DataAccessException {
        LocalDateTime now = LocalDateTime.now();
//        OnSalePoExample example = new OnSalePoExample();
//        example.setOrderByClause("end_time DESC");
//        OnSalePoExample.Criteria criteria = example.createCriteria();
//        criteria.andProductIdEqualTo(productId);
//        criteria.andBeginTimeLessThanOrEqualTo(now);
//        criteria.andEndTimeGreaterThanOrEqualTo(now);
//        List<OnSalePo> onsalePoList = onSalePoMapper.selectByExample(example);
        List<OnSalePoEntity> onsalePoList = onSaleRepository.
                findByProductIdAndBeginTimeLessThanEqualAndEndTimeGreaterThanEqualOrderByEndTimeDesc(productId, now, now);
        return onsalePoList.stream().map(po-> CloneFactory.copy(new OnSale(), po)).collect(Collectors.toList());
    }

    public List<OnSale> getLatestOnSale_Redis(Long productId) throws DataAccessException {
        // Redis 查缓存
        String cacaheKey="OnSale:Latest:"+productId;
        List<OnSale>cachedOnSales=(List<OnSale>)redisTemplate.opsForValue().get(cacaheKey);
        if(null!=cachedOnSales){
            logger.debug("Cache hit for latest OnSale of product id {}",productId);
            return cachedOnSales;
        }

        // 未命中
        LocalDateTime now = LocalDateTime.now();
        OnSalePoExample example = new OnSalePoExample();
        example.setOrderByClause("end_time DESC");
        OnSalePoExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        criteria.andBeginTimeLessThanOrEqualTo(now);
        criteria.andEndTimeGreaterThanOrEqualTo(now);
        List<OnSalePo> onsalePoList = onSalePoMapper.selectByExample(example);

        // Redis 写缓存
        redisTemplate.opsForValue().set(cacaheKey,onsalePoList,10, TimeUnit.MINUTES);
        return onsalePoList.stream().map(po-> CloneFactory.copy(new OnSale(), po)).collect(Collectors.toList());
    }
}
