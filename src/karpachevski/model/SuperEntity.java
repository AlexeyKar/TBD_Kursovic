package karpachevski.model;

/*
 * Базовый класс для всех сущностей
 */
public class SuperEntity {

	private Long id; // идентификатор
	//private Boolean deleted; // 
	
	public SuperEntity() {
		id = -1L;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}