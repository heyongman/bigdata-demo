package com.he.modules.service.impl;

import com.he.modules.dao.MovieDao;
import com.he.modules.entity.Movie;
import com.he.modules.service.MovieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MovieServiceImpl implements MovieService{
    @Resource
    private MovieDao movieDao;

    @Override
    public List<Movie> queryMovieBo() {
        return movieDao.queryMovieBo();
    }

    @Override
    public List<Movie> queryRegionBo() {
        return movieDao.queryRegionBo();
    }
}
