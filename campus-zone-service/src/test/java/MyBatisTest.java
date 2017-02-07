import com.jobcenter.campus.dao.GmPackageHistoryDao;
import com.jobcenter.campus.entity.GmPackageHistory;
import com.jobcenter.campus.mapper.GmPackageHistoryMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * <br>==========================
 * <br> company：job-center
 * <br> system：campus-zone
 * <br> User：lzy.clement
 * <br> Date：2017/1/25
 * <br>==========================
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-config-service.xml"})
public class MyBatisTest {

    @Autowired
    private GmPackageHistoryDao gmPackageHistoryDao;

    @org.junit.Test
    public void test(){
        GmPackageHistory gmPackageHistory = new GmPackageHistory();
        gmPackageHistory.setId(99999999);
        gmPackageHistory.setGameId(1234);
        gmPackageHistory.setPlatformId(2);
        gmPackageHistory.setPackageId(1234);
        boolean flag = false;
        if(flag) {
            gmPackageHistoryDao.insertMapper(gmPackageHistory);
        } else {
            gmPackageHistoryDao.insertOriMapper(gmPackageHistory);
        }
    }

}
