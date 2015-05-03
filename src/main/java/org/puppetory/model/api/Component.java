package org.puppetory.model.api;

import java.util.List;

public interface Component {
	public List<Fact> getFacts();
    public Fact getFact(String name);
}
