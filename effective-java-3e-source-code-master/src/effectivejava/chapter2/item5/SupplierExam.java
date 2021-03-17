package effectivejava.chapter2.item5;

import java.util.Random;
import java.util.function.Supplier;

public class SupplierExam {

    public static void main(String[] args) {
        Mosaic 기본타일_모자이크 = Mosaic.create(new Tile());

        // 사용자가 만든 팩토리를 받아서 처리 할 수 있다
        Supplier<Tile> tileFactory = () -> {
            String randomTileName = "타일" + new Random().nextInt(100);
            return new CustomTile(randomTileName);
        };
        Mosaic 타일2_모자이크 = Mosaic.create(tileFactory);
        Mosaic 타일2_모자이크2 = Mosaic.create(() -> new CustomTile("타일2"));
    }
}

class Mosaic {
    private final Tile tile;

    public Mosaic(Tile tile) {
        System.out.println("create mosaic by " + tile);
        this.tile = tile;
    }

    static Mosaic create(Supplier<? extends Tile> tileFactory) {
        Tile tile = tileFactory.get();
        return new Mosaic(tile);
    }

    static Mosaic create(Tile tile) {
        return new Mosaic(tile);
    }
}

class Tile {
    private final String name;

    public Tile() {
        this("기본타일");
    }

    public Tile(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "name='" + name + '\'' +
                '}';
    }
}

class CustomTile extends Tile {
    public CustomTile(String name) {
        super(name);
    }
}