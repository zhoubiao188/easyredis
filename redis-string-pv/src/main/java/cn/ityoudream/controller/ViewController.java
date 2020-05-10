package cn.ityoudream.controller;

import cn.ityoudream.service.PvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description: 文章Pv
 * @author: zhoubiao
 * @create: 2020-05-10 15:40
 **/
@Controller
public class ViewController {
    @Autowired
    private PvService pvService;
    @GetMapping("/index/{id}")
    public String indexPage(@PathVariable int id, Model model) {
        long view = pvService.view(id);
        model.addAttribute("article", view);
        return "index";
    }
}
