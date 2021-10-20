package gilko.marcin.Auctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gilko.marcin.Auctions.participant.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{

}
