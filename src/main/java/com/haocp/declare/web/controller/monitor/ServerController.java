package com.haocp.declare.web.controller.monitor;

import com.haocp.declare.core.controller.BaseController;
import com.haocp.declare.core.domain.AjaxResult;
import com.haocp.declare.web.model.monitor.Server;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 * 
 * @author haocp
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController extends BaseController
{
    @GetMapping()
    public AjaxResult getInfo() throws Exception
    {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
