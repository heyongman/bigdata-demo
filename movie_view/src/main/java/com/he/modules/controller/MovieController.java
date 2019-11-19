package com.he.modules.controller;

import com.he.modules.entity.Movie;
import com.he.modules.service.MovieService;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/")
public class MovieController {
    @Resource(name = "movieServiceImpl")
    private MovieService movieService;

    @RequestMapping(value = "movieBo",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String  queryMovieBo(Model model) {
        try {
//        查询电影票房
            List<Movie> movieBoList = movieService.queryMovieBo();
//        查询地区票房
//        List<Movie> regionBoList = movieService.queryRegionBo();
            JSONArray movieArray = new JSONArray();
            for (Movie movieBo : movieBoList) {
                JSONObject o = new JSONObject();
                o.put("name", movieBo.getName());
                o.put("bo",movieBo.getBo());
                movieArray.put(o);
            }
            JSONObject movieBo = new JSONObject();
            movieBo.put("movieBo",movieArray);
            return movieBo.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "regionBo",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String  queryRegionBo(Model model) {
        try {
//        查询电影票房
//            List<Movie> movieBoList = movieService.queryRegionBo();
//        查询地区票房
        List<Movie> regionBoList = movieService.queryRegionBo();
            JSONArray movieArray = new JSONArray();
            for (Movie regionBo : regionBoList) {
                JSONObject o = new JSONObject();
                o.put("name", regionBo.getName());
                o.put("bo",regionBo.getBo());
                movieArray.put(o);
            }
            JSONObject regionBo = new JSONObject();
            regionBo.put("regionBo",movieArray);
            return regionBo.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
