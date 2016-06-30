package reports;

public class SecondReportResult {

    private Double result;

    public SecondReportResult() {}
    public SecondReportResult(Double result) {
        this.result = result;
    }

    public void print()
    {
        System.out.println(result);
    }
}
