import com.jobcenter.campus.dao.UserDao;
import com.jobcenter.campus.entity.User;
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
    private UserDao userDao;

    @org.junit.Test
    public void test(){
        User user = new User();
        user.setUsername("test");
        user.setUserpassword("testp");
        boolean flag = false;
        if(flag) {
            userDao.insertUser(user);
        } else {
            userDao.insertOriUser(user);
        }
    }

}
