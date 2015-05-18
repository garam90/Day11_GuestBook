package com.sds.icto.guestbook.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.guestbook.dao.GuestBookDAO;
import com.sds.icto.guestbook.vo.GuestBookVO;

@Controller
public class GuestBookController {

	@Autowired
	GuestBookDAO guestBookDAO;
	
	@RequestMapping("/index")
	public String index(Model model){
		
		ArrayList<GuestBookVO> list = new ArrayList<GuestBookVO>();
		list = guestBookDAO.guestBookList();
		model.addAttribute("list", list);
		return "/views/index.jsp";
		
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@RequestParam String name, @RequestParam String password, @RequestParam("content") String message){
		
		GuestBookVO vo = new GuestBookVO();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		
		guestBookDAO.insert(vo);
		
		return "redirect:/index";
	}
}
