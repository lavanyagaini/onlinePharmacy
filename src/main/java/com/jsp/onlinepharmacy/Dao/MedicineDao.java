package com.jsp.onlinepharmacy.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.entity.Medicine;
import com.jsp.onlinepharmacy.repository.MedicineRepo;



@Repository
public class MedicineDao {

	@Autowired
	private MedicineRepo repo;

	public Medicine saveMedicine(Medicine medicine) {
		
		return repo.save(medicine);
	}

	public Medicine updateMdicine(int medicineId, Medicine medicine) {
		Optional<Medicine> optional=repo.findById(medicineId);
		if(optional.isPresent()) {
			medicine.setMedicineId(medicineId);
			medicine.setMedicalStore(optional.get().getMedicalStore());
			return repo.save(medicine);
		}
		return null;
	}

	public Medicine getMedicineById(int medicineId) {
		Optional<Medicine> optional=repo.findById(medicineId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Medicine deleteMedicine(int medicineId) {
	Optional<Medicine> optional=repo.findById(medicineId);
	if(optional.isPresent()) {
		repo.deleteById(medicineId);
	}
		return optional.get();
	}

	
}
