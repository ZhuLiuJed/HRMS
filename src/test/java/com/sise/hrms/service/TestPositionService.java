package com.sise.hrms.service;

import com.sise.hrms.po.Position;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by holyfrans on 2017/3/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class TestPositionService {
    @Autowired
    private PositionService positionService;

    @Test
    public void testFindAllPositionByPage(){
        Page result = positionService.findAllPositionByPage(1, 1);
        System.out.println(result.getContent());
    }
    @Test
    public void testFindAllPositionByPage1(){
        Page result = positionService.findAllPositionByNameLikeAndPage(1, 1, "t");
        System.out.println(result.getContent());
    }
    @Test
    public void testFindOnePosition(){
        Position result = positionService.findOnePosition(1);
        System.out.println(result);
    }
    @Test
    public void testSavePosition(){
        Position position = new Position();
        position.setId(3);
        position.setPositionName("test");
        positionService.savePosition(position);
    }
    @Test
    public void testDeletePositionById(){
        positionService.deletePositionById(3);
    }
}
