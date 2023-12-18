package com.workintech.s18d1.controller;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.repository.BurgerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/burger")
public class BurgerController {

    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @PostMapping("/")
    public Burger save(@RequestBody Burger burger){
        return burgerDao.save(burger);
    }
    @GetMapping
    public List<Burger> findAll(){
        return burgerDao.findAll();
    }
    @GetMapping("/{id}")
    public Burger getByOd(@PathVariable long id){
        return burgerDao.findById(id);
    }
    @PutMapping
    public Burger update(@RequestBody Burger burger){
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public Burger remove(@PathVariable long id){
        return burgerDao.remove(id);
    }
    @GetMapping("/breadType/{breadType}")
    public List<Burger> getByOd(@PathVariable String breadType){
        BreadType bt = BreadType.valueOf(breadType);
        return burgerDao.findByBreadType(bt);
    }
    @GetMapping("/price/{price}")
    public List<Burger> getByOd(@PathVariable Integer price){
        return burgerDao.findByPrice(price);
    }
    @GetMapping("/content/{content}")
    public List<Burger> findByPrice(@PathVariable String content) {
        return burgerDao.findByContent(content);
    }
}
