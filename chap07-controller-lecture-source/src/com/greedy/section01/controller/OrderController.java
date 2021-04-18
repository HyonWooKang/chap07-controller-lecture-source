package com.greedy.section01.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.greedy.section01.model.dto.CategoryDTO;
import com.greedy.section01.model.dto.MenuDTO;
import com.greedy.section01.model.dto.OrderDTO;
import com.greedy.section01.model.dto.OrderMenuDTO;
import com.greedy.section01.model.service.OrderService;
import com.greedy.section01.view.ResultView;

public class OrderController {

	private OrderService orderService = new OrderService();
	
	public List<CategoryDTO> selectAllCategory() {
		
		// 방식 1.
//		List<CategoryDTO> categoryList = orderService.selectAllCategory();
//		return categoryList;
		
		// 방식 2.
		return orderService.selectAllCategory();
	}

	public List<MenuDTO> selectMenuBy(int categoryCode) {
		
		return orderService.selectMenuBy(categoryCode);
	}

	public void registOrder(Map<String, Object> requestMap) {
		/*
		 * 컨트롤러가 하는 역할은 뷰에서 사용자가 입력한 정보를 파라미터 형태로 전달받으면
		 * 전달받은 값들을 검증하거나 추가적인 정보가 필요한 경우 가공을 한 후에
		 * 서비스쪽으로 전달하기 위한 인스턴스에 담고 서비스의 비즈니스 로직을 담당하는 메소드를 호출
		 * 
		 * 또한 호출한 수행 결과를 반환받아 어떠한 뷰를 다시 사용자에게 보여줄 것인지를 결정하는 역할을 한다.
		 */

		/* 1. 뷰에서 전달받은 파라미터를 꺼내서 변수에 담기 */
		int totalOrderPrice = (Integer) requestMap.get("totalOrderPrice");
		// object donwcasting 진행
		List<OrderMenuDTO> orderMenuList = (List<OrderMenuDTO>)requestMap.get("orderMenuList");
		// object 타입 맞춰주기
		
		/* 2. 추가적으로 필요한 값 있는 경우 생성하기 */
		// chap06. view에서 가져오기
		java.util.Date orderTime = new java.util.Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		
		String date = dateFormat.format(orderTime);
		String time = timeFormat.format(orderTime);
		
		/* 서비스쪽으로 전달하기 위해 DTO 인스턴스에 담기 */
		OrderDTO order = new OrderDTO();
		order.setDate(date);
		order.setTime(time);
		order.setTotalOrderPrice(totalOrderPrice);
		order.setOrderMenuList(orderMenuList);
		
		/* 4. 서비스(비즈니스 로직)을 호출하고 결과를 리턴받는다. */
		int result = orderService.registOrder(order);
		
		/* 5. 서비스 처리 결과를 이용해서 성공, 실패 여부를 판단해서 사용자에게 보여줄 뷰를 결정한다. */
		ResultView resultView = new ResultView();
		if(result > 0) {
			
			resultView.success();
		
		} else {
			
			resultView.failed();
		}
		
	}

}
