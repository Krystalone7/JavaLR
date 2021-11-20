package document;

import functions.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class TabulatedFunctionDoc implements TabulatedFunction{
    private TabulatedFunction tabulatedFunction;
    private String nameOfFile;
    private Boolean modified = false;
    private Boolean fileNameAssigned = false;
    private FXMLMainFormController ctrl;

    public String getNameOfFile() {
        return nameOfFile;
    }


    public void newFunction(double leftX, double rightX, int pointsCount){
        tabulatedFunction = new ArrayTabulatedFunction(leftX, rightX, pointsCount);
    }

    public void tabulateFunction(Function function, double leftX, double rightX, int pointsCount) {
        tabulatedFunction = TabulatedFunctions.tabulate(function, leftX, rightX, pointsCount);
    }

    public void saveFunctionAs(String fileName) {
        nameOfFile = fileName;
        fileNameAssigned = true;
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONArray funct = new JSONArray();
        for (int i = 0; i < tabulatedFunction.getPointsCount(); i++) {
            jsonArray.add(tabulatedFunction.getPointX(i));
            jsonArray.add(tabulatedFunction.getPointY(i));
            jsonObject.put("p" + i, jsonArray);
            jsonArray = new JSONArray();
        }
        funct.add(jsonObject);
        try (FileWriter writer = new FileWriter(nameOfFile + ".json")) {
            writer.write(funct.toJSONString());
            writer.flush();
            writer.close();
            modified = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFunction(String fileName) {
        try (FileReader reader = new FileReader(fileName + ".json")) {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject point = (JSONObject) jsonArray.get(0);
            FunctionPoint[] arr = new FunctionPoint[point.size()];
            FunctionPoint p = new FunctionPoint();
            nameOfFile = fileName;
            for (int i = 0; i < point.size(); i++) {
                JSONArray val = (JSONArray) point.get("p" + i);
                p.setX(Double.parseDouble(val.get(0).toString()));
                p.setY(Double.parseDouble(val.get(1).toString()));
                arr[i] = new FunctionPoint(p);
            }
            tabulatedFunction = new ArrayTabulatedFunction(arr);
            //System.out.println(tabulatedFunction);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    public void saveFunction(){
        if(fileNameAssigned){
            saveFunctionAs(nameOfFile);
        }
    }

    public void registerRedrawFunctionController(FXMLMainFormController fxmlMainFormController){
        ctrl = fxmlMainFormController;
        callRedraw();
    }

    public void callRedraw(){
        ctrl.redraw();
    }

    @Override
    public int getPointsCount() {
        return tabulatedFunction.getPointsCount();
    }
    @Override
    public FunctionPoint getPoint(int index) throws FunctionPointIndexOutOfBoundsException {
        return tabulatedFunction.getPoint(index);
    }
    @Override
    public void setPoint(int index, FunctionPoint point) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        tabulatedFunction.setPoint(index, point);
        modified = true;
        callRedraw();
    }
    @Override
    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException {
        return tabulatedFunction.getPointX(index);
    }
    @Override
    public void setPointX(int index, double x) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        tabulatedFunction.setPointX(index, x);
        modified = true;
        callRedraw();
    }
    @Override
    public double getPointY(int index) throws FunctionPointIndexOutOfBoundsException {
        return tabulatedFunction.getPointY(index);
    }
    @Override
    public void setPointY(int index, double y) throws FunctionPointIndexOutOfBoundsException {
        tabulatedFunction.setPointY(index, y);
        modified = true;
        callRedraw();
    }
    @Override
    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException, IllegalStateException {
        tabulatedFunction.deletePoint(index);
        modified = true;
        callRedraw();
    }
    @Override
    public void addPoint(FunctionPoint point)  {
        try {
            tabulatedFunction.addPoint(point);
            callRedraw();
            modified = true;
        } catch (InappropriateFunctionPointException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Object clone(){
        TabulatedFunctionDoc clone = new TabulatedFunctionDoc();
        clone.tabulatedFunction = (TabulatedFunction) tabulatedFunction.clone();
        clone.nameOfFile = nameOfFile;
        clone.modified = modified;
        clone.fileNameAssigned = fileNameAssigned;
        return clone;
    }
    @Override
    public int hashCode() {
        return tabulatedFunction.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof TabulatedFunction) {
            if (((TabulatedFunction) obj).getPointsCount() != getPointsCount()) return false;
            for (int i = 0; i < getPointsCount(); i++) {
                if (!(getPoint(i).equals(((TabulatedFunction) obj).getPoint(i)))) return false;
            }
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getPointsCount(); i++) {
            builder.append(getPoint(i).toString()).append(", ");
        }
        builder.deleteCharAt(builder.length() - 1).deleteCharAt(builder.length() - 1);
        return "[" + builder + "]";
    }
    @Override
    public double getLeftDomainBorder() {
        return tabulatedFunction.getLeftDomainBorder();
    }
    @Override
    public double getRightDomainBorder() {
        return tabulatedFunction.getRightDomainBorder();
    }
    @Override
    public double getFunctionValue(double x) {
        return tabulatedFunction.getFunctionValue(x);
    }

    public boolean isModified() {
        return modified;
    }

    @Override
    public Iterator<FunctionPoint> iterator() {
        return new Iterator<>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < tabulatedFunction.getPointsCount();
            }

            @Override
            public FunctionPoint next() {
                if(index >= tabulatedFunction.getPointsCount()){
                    throw new NoSuchElementException();
                }
                return new FunctionPoint(tabulatedFunction.getPoint(index++));
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
