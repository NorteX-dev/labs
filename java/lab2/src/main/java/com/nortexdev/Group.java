package com.nortexdev;

import java.util.List;

public class Group {
	private String groupName;
	private List<Person2> members;

	public Group(String groupName, List<Person2> members) {
		this.groupName = groupName;
		this.members = members;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Person2> getMembers() {
		return members;
	}

	public void setMembers(List<Person2> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "Group{" +
			"groupName='" + groupName + '\'' +
			", members=" + members +
			'}';
	}
}
