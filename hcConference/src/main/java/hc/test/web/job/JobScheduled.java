package hc.test.web.job;


import hc.test.web.controller.demo.ApplyController;
import hc.test.web.service.demo.IApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class JobScheduled {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApplyService applyService;

    /**
     * @desc：每天23:30同步金蝶采购订单数据到内部数据库（10.1.1.23的mysql上）
     * @author：yangj
     * @date：2019/5/31 0027 08:55
     * 0 0 12 ? * WED 表示每个星期三中午12点
     * 天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
     * @Scheduled(cron = "0 50 10 * * ?")
     */
   /* @Scheduled(cron = "0/60 * * * * ?")
    public void syncPoOrder() {
        logger.info("定时任务开始：增量获取金蝶采购订单数据");
        try {
            long sta = System.currentTimeMillis();
            applyService.findByDate();
            long end = System.currentTimeMillis();
            logger.info("定时任务结束：增量获取金蝶采购订单数据，总耗时：" + (end - sta) / 1000 + "s");
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }*/

    /**
     * @description：定时同步金蝶物料数据
     * @author：justin
     * @date：2019-08-13 15:17
     */
    /*@Scheduled(cron = "0 40 23 * * ?")
    public void syncMara() {
        logger.info("定时任务开始：增量获取金蝶物料数据");
        try {
            long sta = System.currentTimeMillis();
            long end = System.currentTimeMillis();
            logger.info("定时任务结束：增量获取金蝶物料数据，总耗时：" + (end - sta) / 1000 + "s");
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }*/

}
