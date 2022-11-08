package ais;

import java.nio.charset.Charset;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ais.exceptions.InvalidKeyException;
import ais.helpers.LandPlotHelper;
import ais.models.LandPlot;
import ais.services.LandPlotService;

@SpringBootTest
public class LandPlotServiceTest {
	@Autowired
	LandPlotService landPlotService;
	Random r = new Random();

	String randomString() {
		byte[] array = new byte[r.nextInt(100)];
		new Random().nextBytes(array);
		return new String(array, Charset.forName("UTF-8"));
	}

	// build,edit,getLandPlot,getAll,edit
	@Test
	void testBuild() {
		for (int i = 1; i <= 1000; i++) {
			Double area = r.nextDouble();
			String str = randomString();
			Integer x = r.nextInt();
			Integer y = r.nextInt();
			LandPlotHelper landPlotHelper = new LandPlotHelper(area, str, x, y);
			LandPlot landPlot = new LandPlot(null, area, str, x, y, null);
			Assertions.assertEquals(landPlot, landPlotService.build(landPlotHelper));
		}
	}

	@Test
	void testEdit() {
		for (int i = 1; i <= 1000; i++) {
			LandPlot land1 = new LandPlot();
			land1.setId(null);
			LandPlot land2 = new LandPlot();
			land2.setId(r.nextLong(1000000000000l, 9000000000000l));

			Assertions.assertThrows(InvalidKeyException.class, () -> {
				landPlotService.edit(land1);
			});
			Assertions.assertThrows(InvalidKeyException.class, () -> {
				landPlotService.edit(land2);
			});
		}
	}

}
