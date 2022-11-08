package ais;

import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ais.helpers.TimeSlotHelper;
import ais.models.TimeSlot;
import ais.services.TimeSlotService;
@SpringBootTest
public class TimeSlotTests {

	// Build, add
	@Autowired
	TimeSlotService timeSlotService;
	Random r = new Random();

	@Test
	void testBuild() {
		for (int i = 1; i <= 1000; i++) {

			TimeSlotHelper helper = new TimeSlotHelper(1l, (byte) r.nextInt(0, 100), r.nextInt(0, 23), r.nextInt(0, 59),
					r.nextInt(0, 23), r.nextInt(0, 59), r.nextInt());
			TimeSlot timeSlot = new TimeSlot(null, null, helper.getDay(), helper.getInitialHour(),
					helper.getInitialMinute(), helper.getFinalHour(), helper.getFinalMinute(), null, null,
					helper.getAmountOfWater());
			Assertions.assertEquals(timeSlotService.build(helper), timeSlot);
		}
	}

	@Test
	void testIsIntersected() {

		/*		initialHour		initialMinute	finalHour	finalMinute
		3				20				4			30
		2				0				3			0
		10				0				13			0
		22				0				23			0
		7				0				9			0
		5				20				5			30
		5				40				5			50
		1				20				2			0
		14				0				15			0
		15				20				15			50 
		17				0				20			0
		*/
	
		TimeSlotHelper[] helpers = {new TimeSlotHelper(1l, (byte) 0, 3, 19, 3, 21, 100),
				new TimeSlotHelper(1l,(byte)0,20,0,22,30,100)};
		for(TimeSlotHelper helper : helpers)
			Assertions.assertTrue(timeSlotService.isIntersected(helper));
	}
}
