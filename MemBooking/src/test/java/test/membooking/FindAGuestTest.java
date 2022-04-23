package test.membooking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javaforever.membooking.domain.Guest;
import com.javaforever.membooking.service.GuestService;
import com.javaforever.membooking.serviceimpl.GuestServiceImpl;

import junit.framework.Assert;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.javaforever.membooking.Application.class})
public class FindAGuestTest extends TestCase{
    @SuppressWarnings("deprecation")
	@Test
    public void test() throws Exception{
    	GuestService gService = new GuestServiceImpl();
    	Guest jerry = gService.findGuestById(1L);
    	Assert.assertEquals("Jerry", jerry.getGuestName());
    }
}
