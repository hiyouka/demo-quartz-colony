package com.jy.controller;

import com.jy.model.TaskInfo;
import com.jy.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务管理
 */
@Controller
@RequestMapping("/qy/api/task/")
public class TaskManageController {

    private Logger logger = LoggerFactory.getLogger(TaskManageController.class);

    @Autowired
    private TaskService taskService;

    /**
     * Index.jsp
     */
    @RequestMapping(value={"", "/", "index"})
    public String info(){
        return "index.jsp";
    }

    /**
     * 任务列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="list")
    public Map<String, Object> list(){
        Map<String, Object> map = new HashMap<>();
        List<TaskInfo> infos = taskService.list();
        map.put("rows", infos);
        map.put("total", infos.size());
        return map;
    }

    /**
     * 保存定时任务
     * @param info
     */
    @ResponseBody
    @RequestMapping(value="save", produces = "application/json; charset=UTF-8")
    public String save(@RequestBody TaskInfo info){
        try {
            if(info.getId() == 0) {
                if(info.getType().equals("sync")) {
                    info.setJobBeanName("remoteJobSync");
                }else {
                    info.setJobBeanName("remoteJobASync");
                }
                taskService.addJob(info);
            }else{
                taskService.edit(info);
            }
        } catch (Exception e) {
            logger.info("save job error: {}",e);
            return e.getMessage();
        }
        return "成功";
    }

    /**
     * 删除定时任务
     * @param jobName
     * @param jobGroup
     */
    @ResponseBody
    @RequestMapping(value="delete", produces = "application/json; charset=UTF-8")
    public String delete(String jobName,String jobGroup){
        try {
            taskService.delete(jobName, jobGroup);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "成功";
    }

    /**
     * 暂停定时任务
     * @param jobName
     * @param jobGroup
     */
    @ResponseBody
    @RequestMapping(value="pause/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
    public String pause(@PathVariable String jobName, @PathVariable String jobGroup){
        try {
            taskService.pause(jobName, jobGroup);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "成功";
    }

    /**
     * 重新开始定时任务
     * @param jobName
     * @param jobGroup
     */
    @ResponseBody
    @RequestMapping(value="resume/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
    public String resume(@PathVariable String jobName, @PathVariable String jobGroup){
        try {
            taskService.resume(jobName, jobGroup);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "成功";
    }
}