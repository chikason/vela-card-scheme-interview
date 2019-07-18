package com.vela.work;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class VelaTestApplicationTests {

      @Autowired
      private CardRepository cardRepo;

      @Autowired
      private StatRepository statRepo;

      @Autowired
      private TestEntityManager entityManager;


	@Test
	public void contextLoads() {
	}

        //<summary> checks or tests the working of Card Repository interface </summary>
        @Test
        public void testCard(){
          //set data
          Card card = new Card(1,519904,"Visa","credit",null);
          entityManager.persist(card);
          
          //find data from table
          Card card1 = cardRepo.findByCardNo(card.getCardNo());

          //check 
          assertThat(card1.getScheme())
                   .isEqualTo(card.getScheme());

   }

}
