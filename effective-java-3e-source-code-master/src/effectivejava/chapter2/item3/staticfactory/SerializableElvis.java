package effectivejava.chapter2.item3.staticfactory;

import java.io.*;
import java.util.Base64;

// 코드 3-2 정적 팩터리 방식의 싱글턴에 직렬화를 적용한 코드
public class SerializableElvis implements Serializable {
    private static final SerializableElvis INSTANCE = new SerializableElvis();
    transient private int value;

    private SerializableElvis() {
        this.value = 100;
    }
    public static SerializableElvis getInstance() { return INSTANCE; }

    private Object readResolve() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    public String serialize() throws IOException {
        byte[] serializedMember;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(INSTANCE);
                // serializedMember -> 직렬화된 member 객체
                serializedMember = baos.toByteArray();
            }
        }
        return Base64.getEncoder().encodeToString(serializedMember);
    }

    public SerializableElvis deserialize(String base64) throws IOException, ClassNotFoundException {
        byte[] serializedElvis = Base64.getDecoder().decode(base64);
        try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedElvis)) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                Object objectElvis = ois.readObject();
                return (SerializableElvis) objectElvis;
            }
        }
    }

    @Override
    public String toString() {
        return "SerializableElvis{" +
                "value=" + value +
                '}';
    }

    // 이 메서드는 보통 클래스 바깥(다른 클래스)에 작성해야 한다!
    public static void main(String[] args) {
        SerializableElvis elvis = SerializableElvis.getInstance();
        try {
            String serialize = elvis.serialize();
            System.out.println("serialize elvis : " + serialize);
            System.out.println("serialize elvis : " + elvis.deserialize(serialize));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        elvis.leaveTheBuilding();
    }
}
