package com.example.gz.controller;


import com.common.entity.TxLcA;
import com.common.entity.TxLcB;
import com.example.gz.service.ITxLcAService;
import com.example.gz.service.ITxLcBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gz
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/gz/tx-lc-b")
public class TxLcBController {
    @Autowired
    private ITxLcBService txLcBService;

    @PostMapping("/add")
    public Object add(@RequestBody TxLcA txLcA){
        TxLcB txLcB = new TxLcB();
        txLcB.setLcbName(txLcA.getLcaName());
        txLcB.setLcbVersion(txLcA.getLcaVersion());
        boolean b = txLcBService.saveAdd(txLcB);
        return b;
    }
}
