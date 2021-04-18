package com.greedy.section01.run;

import com.greedy.section01.view.OrderMenu;

public class Application {
	
	public static void main(String[] args) {

		/* chapter06은 MVC1 구조이다.
		 * MVC는 Make View Control의 약자로
		 * VIEW에 내용을 몰아서 넣어주는 구조이다. 
		 */
		
		OrderMenu orderMenu = new OrderMenu();
		orderMenu.displayMainMenu();
		
		
		
	}

}
