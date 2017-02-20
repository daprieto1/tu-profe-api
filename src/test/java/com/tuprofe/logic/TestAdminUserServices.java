package com.tuprofe.logic;

import com.tuprofe.api.entities.AdminUser;
import com.tuprofe.api.logic.services.IAdminUserServices;
import com.tuprofe.api.persistance.repositories.IAdminUserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author diego
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestAdminUserServices {

    public static final String ADMIN_USER_NAME = "ADMIN_USER_NAME";
    public static final String ADMIN_USER_LAST_NAME = "ADMIN_USER_LAST_NAME";
    public static final String ADMIN_USER_EMAIL = "ADMIN_USER_EMAIL";
    public static final String ADMIN_USER_PASSWORD = "$2a$10$lN4iMJagv5x8gdUulNGhwuNMnms33QlIg5rYwAnRUMXzcYPPSwO5u";

    private List<AdminUser> adminUsers;

    @Autowired
    @Qualifier("DynamoAdminUserRepository")
    private IAdminUserRepository adminUserRepository;

    @Autowired
    @Qualifier("AdminUserServices")
    private IAdminUserServices adminUserServices;

    public static final AdminUser createAdminUserTemplate() {
        AdminUser adminUser = new AdminUser(ADMIN_USER_NAME, ADMIN_USER_LAST_NAME, ADMIN_USER_EMAIL, ADMIN_USER_PASSWORD);
        return adminUser;
    }

    @Before
    public void initTest() {
        adminUsers = new ArrayList<>();
    }

    @Test
    public void testCreate() {
        try {
            AdminUser adminUser1 = TestAdminUserServices.createAdminUserTemplate();
            adminUser1 = adminUserServices.create(adminUser1);
            adminUsers.add(adminUser1);

            assertNotNull("The adminUser1 ID must not be null", adminUser1.getId());

            AdminUser adminUser2 = adminUserServices.find(adminUser1.getId());

            assertNotNull("The teacher 2 must not be null", adminUser2);
            assertTrue("The teacher id must be equal", adminUser1.getId().equals(adminUser2.getId()));
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testCreate : " + e.getMessage();
            fail(fail);
        }
    }

    @After
    public void tearDown() {
        adminUsers.forEach(au -> adminUserRepository.delete(au));
    }

}
