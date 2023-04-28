package com.momolearn.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momolearn.exception.NotExistException;
import com.momolearn.repository.CartRepository;
import com.momolearn.repository.LecturesRepository;
import com.momolearn.repository.MembersRepository;
import com.momolearn.repository.MyLecturesRepository;
import com.momolearn.dto.CartDTO;
import com.momolearn.dto.PaymentRequestDTO;
import com.momolearn.entity.Cart;
import com.momolearn.entity.Lectures;
import com.momolearn.entity.Members;
import com.momolearn.entity.MyLectures;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepository;

	private final LecturesRepository lecturesRepository;

	private final MembersRepository membersRepository;

	private final MyLecturesRepository myLecturesRepository;

	private ModelMapper mapper = new ModelMapper();

	public boolean checkCart(int lecId, String memId) {

		List<Cart> cart = cartRepository.findByLectureId(lecId);

		for (int i = 0; i < cart.size(); i++) {
			
			if (cart.get(i).getMember().getMemId().equals(memId)) {

				return true;
			}

		}
		return false;
	}

	@Transactional
	public CartDTO addCart(int lecId, String memId) throws NotExistException {

		Members member = membersRepository.findById(memId).orElseThrow(() -> new NotExistException("존재하는 회원이 없습니다."));

		Lectures lecture = lecturesRepository.findById(lecId);

		Cart cart = cartRepository.save(new Cart(member, lecture));

		return mapper.map(cart, CartDTO.class);
	}

	public List<CartDTO> getCart(String memId) {

		List<Cart> cart = cartRepository.findByMemberMemId(memId);

		return Arrays.asList(mapper.map(cart, CartDTO[].class));
	}

	@Transactional
	public void getMyLectures(PaymentRequestDTO request) throws NotExistException {

		for (int i = 0; i < request.getCheckedTitles().size(); i++) {

			Lectures lecture = lecturesRepository.findByTitleContaining(request.getCheckedTitles().get(i)).get(0);

			if (lecture == null) {

				throw new NotExistException("강의를 찾을 수 없습니다.");

			}

			Members member = membersRepository.findById(request.getMemId())
					.orElseThrow(() -> new NotExistException("존재하는 회원이 없습니다."));

			lecture.setApplyCnt(lecture.getApplyCnt() + 1);
			lecturesRepository.save(lecture);

			myLecturesRepository.save(new MyLectures(member, lecture));
			int result = cartRepository.deleteByMemberAndLecture(member, lecture);

			if (result == 0) {

				throw new NotExistException("수강신청에 실패했습니다. 관리자에게 문의하십시오.");

			}

		}

	}

	@Transactional
	public void deleteCart(PaymentRequestDTO request) throws NotExistException {
		
		for (int i = 0; i < request.getCheckedTitles().size(); i++) {

			Lectures lecture = lecturesRepository.findByTitleContaining(request.getCheckedTitles().get(i)).get(0);

			if (lecture == null) {

				throw new NotExistException("강의를 찾을 수 없습니다.");

			}

			Members member = membersRepository.findById(request.getMemId())
					.orElseThrow(() -> new NotExistException("존재하는 회원이 없습니다."));

			int result = cartRepository.deleteByMemberAndLecture(member, lecture);

			if (result == 0) {

				throw new NotExistException("수강바구니에서 강의를 삭제할 수 없습니다. 관리자에게 문의하십시오.");

			}

		}

	}

}
