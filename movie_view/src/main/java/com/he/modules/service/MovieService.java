package com.he.modules.service;

import com.he.modules.entity.Movie;

import java.util.List;

public interface MovieService {
    /**
     * 查询电影票房
     * @return movie
     */
    List<Movie> queryMovieBo();

    /**
     * 查询地区票房
     * @return movie
     */
    List<Movie> queryRegionBo();
}
