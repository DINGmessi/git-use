package cn.edu.scujcc.model;

import java.time.LocalDateTime;

public class Comment {
private String author;
private String content;

private LocalDateTime dt;

private int star;

@Override
public String toString() {
	return "Comment [author=" + author + ", content=" + content + ", dt=" + dt + ", star=" + star + "]";
}

}
