package com.jsp.onlinepharmacy.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.entity.Staff;
import com.jsp.onlinepharmacy.repository.StaffRepo;

@Repository
public class StaffDao {
	
	@Autowired
	private StaffRepo repo;

	public Staff saveStaff(Staff staff) {
		return repo.save(staff);
	}

	public Staff updateStaff(int staffId, Staff staff) {
	Optional<Staff> optional=repo.findById(staffId);
	if(optional.isPresent()) {
		Staff oldStaff=optional.get();
		staff.setStaffId(staffId);
		staff.setAdmin(oldStaff.getAdmin());
		staff.setMedicalStore(oldStaff.getMedicalStore());
		return repo.save(staff);
	}
		return null;
	}
	
	public Staff getStaffById(int staffId) {
		Optional<Staff> optional=repo.findById(staffId);
		if(optional.isPresent()) {
			return optional.get();
		}
			return null;
		}

		public Staff deleteStaffById(int staffId) {
			Optional<Staff> optional=repo.findById(staffId);
			if(optional.isPresent()) {
				repo.deleteById(staffId);
				return optional.get();
			}
			return null;
		}
}

