package sim

import org.junit._
import Assert._


class VectorTest {

	@Test def scalarProduct {
		val v1 = new Vector(2,2)
		val v2 = new Vector(3,3)
		
		assertEquals(12, v1 * v2, 1e-3)
	}
	
	@Test def scalarDivision {
		assertEquals(new Vector(2,2,2,2), new Vector(4,4,4,4) / 2)
	}
	
	@Test def scalarMultiplication {
		import Double2DoubleWrapper._
		assertEquals(new Vector(6,4), new Vector(3,2) * 2)
		assertEquals(new Vector(6,4), 2 * new Vector(3,2))
	}
	
	@Test def dimensionwiseMultiplication {
		assertEquals(new Vector(4,4,4), new Vector(2,2,2) ** new Vector(2,2,2))
	}
	
	@Test def distanceSquared {
		assertEquals(5, new Vector(1,2,3) distanceSquared new Vector(1,1,1),1e-3)
	}
	
	@Test def createVector {
		assertEquals(new Vector(1,2),Vector(1,2))		
	}
	
	@Test def len {
		assertEquals(5, Vector(3,4).len, 1e-3)
	}
	
	@Test def || {
		assertEquals(1, (Vector(3,4) || Vector(5,6)).len, 1e-3)
	}
}