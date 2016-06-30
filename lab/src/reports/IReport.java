package reports;

public interface IReport <Type> {
    public <Type> Type getReport(Parameters params);
}
