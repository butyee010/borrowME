package com.borrow.blockchain.repo;

public interface SequencerRepository {
	Long getNextSequence(String sequenceName) throws Exception;
}
