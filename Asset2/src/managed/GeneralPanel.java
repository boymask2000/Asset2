package managed;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.PieChartModel;

import common.JsfUtil;

public class GeneralPanel extends ABaseBean {

	private PieChartModel pieModel2;

	private int v0;
	private int v1;
	private int v4;
	private int v6;
	private int v5;
	private int v7;
	private int v9;
	private int v8;
	private int v3;
	private int v2;

	@PostConstruct
	public void init() {
		getData();

		createPieModel2();
	}

	private void getData() {
		ManagedAssetBean bean = (ManagedAssetBean) JsfUtil.getBean("managedAssetBean");
		v0 = bean.getAssetsInStatus(0);
		v1 = bean.getAssetsInStatus(1);
		v2 = bean.getAssetsInStatus(2);
		v3 = bean.getAssetsInStatus(3);
		v4 = bean.getAssetsInStatus(4);
		v5 = bean.getAssetsInStatus(5);
		v6 = bean.getAssetsInStatus(6);
		v7 = bean.getAssetsInStatus(7);
		v8 = bean.getAssetsInStatus(8);
		v9 = bean.getAssetsInStatus(9);

	}

	private void createPieModel2() {

		pieModel2 = new PieChartModel();

		pieModel2.set("1-" + v1, v1);
		pieModel2.set("2-" + v2, v2);
		pieModel2.set("3-" + v3, v3);
		pieModel2.set("4-" + v4, v4);
		pieModel2.set("5-" + v5, v5);
		pieModel2.set("6-" + v6, v6);
		pieModel2.set("7-" + v7, v7);
		pieModel2.set("8-" + v8, v8);
		pieModel2.set("9-" + v9, v9);

		pieModel2.setTitle("Asset per outcomes");
		pieModel2.setLegendPosition("e");
		pieModel2.setFill(true);
		pieModel2.setShowDataLabels(true);
		pieModel2.setDiameter(150);
		pieModel2.setShadow(false);
		pieModel2.setSeriesColors("548235, A9D08E, E2EFDA, BF8F00, FFD966, FFF2CC, EB0000,FF4F4F, FFC1C1");
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}

	public int getV0() {
		return v0;
	}

	public int getV1() {
		return v1;
	}

	public int getV4() {
		return v4;
	}

	public int getV6() {
		return v6;
	}

	public int getV5() {
		return v5;
	}

	public int getV7() {
		return v7;
	}

	public int getV9() {
		return v9;
	}

	public int getV8() {
		return v8;
	}

	public int getV3() {
		return v3;
	}

	public int getV2() {
		return v2;
	}

	public void setV0(int v0) {
		this.v0 = v0;
	}

	public void setV1(int v1) {
		this.v1 = v1;
	}

	public void setV4(int v4) {
		this.v4 = v4;
	}

	public void setV6(int v6) {
		this.v6 = v6;
	}

	public void setV5(int v5) {
		this.v5 = v5;
	}

	public void setV7(int v7) {
		this.v7 = v7;
	}

	public void setV9(int v9) {
		this.v9 = v9;
	}

	public void setV8(int v8) {
		this.v8 = v8;
	}

	public void setV3(int v3) {
		this.v3 = v3;
	}

	public void setV2(int v2) {
		this.v2 = v2;
	}

}
