package com.zhurawell.base.api.controllers.dashboards;


import com.fasterxml.jackson.databind.node.BigIntegerNode;
import com.zhurawell.base.data.model.dashboard.Dashboard;
import com.zhurawell.base.data.model.widget.Widget;
import com.zhurawell.base.data.repo.dashboard.DashboardRepo;
import com.zhurawell.base.data.repo.widget.WidgetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardsRestController {

    @Autowired
    private DashboardRepo dashboardRepo;

    @Autowired
    private WidgetRepo widgetRepo;

    @GetMapping("/get/{id}")
    public ResponseEntity getDashboardById(@PathVariable("id") BigInteger id) {
        Dashboard d = dashboardRepo.getById(id);
        Widget w = widgetRepo.getById(id);

        w.setName("Test Test");
        w.setData("{\"color\": \"red\", \"size\": \"20x20\"}");
        widgetRepo.save(w);
        return ResponseEntity.ok().body(d);
    }
}
