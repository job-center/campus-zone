import com.jobcenter.campus.common.redis.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * <br>==========================
 * <br> company：
 * <br> system：campus-zone
 * <br> developer：lzy.clement
 * <br> date：2017/2/7
 * <br>==========================
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-config-service.xml"})
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void test() {
        redisUtils.set("lzy_test","test");
        System.out.println(redisUtils.get("lzy_test"));
    }
}
