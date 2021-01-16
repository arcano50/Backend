package tec.diseno.communities.model;

import java.util.List;

public interface NewsBuilder {
	public List<String> addNews(News news);
	public List<News> getNewsByUser(int id);
}
