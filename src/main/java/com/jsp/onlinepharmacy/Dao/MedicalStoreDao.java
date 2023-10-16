package com.jsp.onlinepharmacy.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.repository.MedicalStoreRepo;

@Repository
public class MedicalStoreDao {

	@Autowired
	private MedicalStoreRepo repo;

	public MedicalStore saveMedicalStore(MedicalStore medicalStore) {
	
		return repo.save(medicalStore);
	}

	public MedicalStore updateMedicalStore(int storeId, MedicalStore medicalStore) {
		//medicalstore having name, manager ,address,
		Optional<MedicalStore> optional=repo.findById(storeId);
		
		if(optional.isPresent()) {
			MedicalStore oldMedicalStore=optional.get();
			medicalStore.setStoreId(storeId);
			//		//medicalstore having id,name, manager ,address,
			medicalStore.setAdmin(oldMedicalStore.getAdmin());
			medicalStore.setAddress(oldMedicalStore.getAddress());
			return repo.save(medicalStore);
		}
		return null;
	}

	public MedicalStore getMedicalStoreById(int storeId) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		
		if(optional.isPresent()) {
		return optional.get();
		}
		return null;
	}

	public MedicalStore deleteMedicalStoreById(int storeId) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			MedicalStore medicalStore=optional.get();
			repo.delete(medicalStore);
			return medicalStore;
			
		}
		return null;
	}
	
}
