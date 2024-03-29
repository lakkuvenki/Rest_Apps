import java.util.HashSet;

public class User {
	private int age;

	public User(int age) {
		this.age = age;
	}

	public boolean equals(Object obj) {
		User user = (User) obj;
		return this.age == user.age;
	}

	public int hashCode() {
		return 1;
	}

	public static void main(String[] args) {
		HashSet<User> hs = new HashSet<User>();
		hs.add(new User(1));
		hs.add(new User(2));
		System.out.println(hs.size());
	}
}
