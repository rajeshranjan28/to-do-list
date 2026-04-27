package com.example.entity;

import jakarta.persistence.*;

@Entity
public class Task {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="id")
	    private int id;

	    @Column(name="taskName")
	    private String taskName;

	    @Column(name="priority")
	    private int priority;

	    @Column(name="category")
	    private String category;

	    @Column(name="completed", nullable=false)
	    private boolean completed = false;
	    
		public Task(int id, String taskName, int priority, String category, boolean completed) {
			super();
			this.id = id;
			this.taskName = taskName;
			this.priority = priority;
			this.category = category;
			this.completed = completed;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTaskName() {
			return taskName;
		}

		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}

		public int getPriority() {
			return priority;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public boolean isCompleted() {
			return completed;
		}

		public void setCompleted(boolean completed) {
			this.completed = completed;
		}
	    
}
