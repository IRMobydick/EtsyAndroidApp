package bo.app;

public enum ev {
    READ_CARDS("read_cards_set", "read_cards_flat"),
    VIEWED_CARDS("viewed_cards_set", "viewed_cards_flat");
    
    final String f420c;
    final String f421d;

    private ev(String str, String str2) {
        this.f420c = str;
        this.f421d = str2;
    }
}
