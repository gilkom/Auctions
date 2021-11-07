package gilko.marcin.Auctions.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gilko.marcin.Auctions.model.participant.Participant;
import gilko.marcin.Auctions.repository.ParticipantRepository;

@Service
@Transactional
public class ParticipantService {

	@Autowired
	private ParticipantRepository repo;
	
	public  List<Participant> listAll(){
		return repo.findAll();
	}
	
	public void save(Participant participant) {
		repo.save(participant);
	}
	
	public Participant get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
