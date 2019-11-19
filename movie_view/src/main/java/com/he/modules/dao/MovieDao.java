package com.he.modules.dao;

import com.he.modules.entity.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Yongman
 * 对Object的DAO操作
 * 包含了通用的一些方法
 */
@Repository
public interface MovieDao {
    /**
     * 查询电影票房
     * @return
     */
    List<Movie> queryMovieBo();

    /**
     * 查询地区票房
     * @return
     */
    List<Movie> queryRegionBo();

}
