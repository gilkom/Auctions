package gilko.marcin.Auctions.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gilko.marcin.Auctions.model.auction.AuctionItem;
import gilko.marcin.Auctions.repository.AuctionItemRepository;

@Service
@Transactional
public class AuctionItemService {

	@Autowired
	private AuctionItemRepository repo;
	
	public List<AuctionItem> listAll(){
		return repo.findAll();
	}
	
	public void save(AuctionItem auctionItem) {
		repo.save(auctionItem);
	}
	
	public AuctionItem get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	public List<AuctionItem> sortedListAll(){
		return repo.findAllByOrderByAuction_item_idDesc();
	}
	
	public List<AuctionItem> sortedListByParticipantId(List<Long> auctionIdList){
		return repo.findAllByParticipantIdDesc(auctionIdList);
	}
}
