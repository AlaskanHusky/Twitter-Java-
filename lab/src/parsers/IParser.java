package parsers;


public interface IParser<Type> {
    public <Type> Type parse(String str);
}
