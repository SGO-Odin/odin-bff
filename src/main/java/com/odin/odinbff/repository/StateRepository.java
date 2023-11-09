package com.odin.odinbff.repository;

import com.odin.odinbff.model.address.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, State.StateAcronyms> {
    State findByAcronym(final State.StateAcronyms stateAcronym);
}
