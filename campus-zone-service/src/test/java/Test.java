import com.jobcenter.campus.dao.GmPackageHistoryMapper;
import com.jobcenter.campus.entity.GmPackageHistory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * <p>
 * <br>==========================
 * <br> 公司：优视科技-游戏中心
 * <br> 系统：九游游戏中心客户端 campus-zone
 * <br> 开发：lzy.clement
 * <br> 创建时间：2017/1/26
 * <br>==========================
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-config-service.xml"})
public class Test {

    @Resource
    private GmPackageHistoryMapper gmPackageHistoryMapper;

    @org.junit.Test
    public void test(){
        GmPackageHistory gmPackageHistory = new GmPackageHistory();
        gmPackageHistory.setId(99999999);
        gmPackageHistory.setGameId(1234);
        gmPackageHistory.setPlatformId(2);
        gmPackageHistory.setPackageId(1234);
        gmPackageHistoryMapper.insert(gmPackageHistory);
    }
}
