/*
 * Copyright (c) 2016 Haulmont
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.company.dashboard.web.mainwindow;

import com.company.dashboard.route.Route;
import com.haulmont.charts.gui.components.charts.PieChart;
import com.haulmont.charts.gui.components.charts.SerialChart;
import com.haulmont.charts.gui.components.map.MapViewer;
import com.haulmont.charts.gui.data.ListDataProvider;
import com.haulmont.charts.gui.data.MapDataItem;
import com.haulmont.charts.gui.map.model.GeoPoint;
import com.haulmont.charts.gui.map.model.InfoWindow;
import com.haulmont.charts.gui.map.model.Marker;
import com.haulmont.charts.gui.map.model.base.MarkerImage;
import com.haulmont.cuba.gui.ComponentsHelper;
import com.haulmont.cuba.gui.components.AbstractMainWindow;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.components.mainwindow.SideMenu;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.web.Connection;
import com.haulmont.cuba.web.WebWindowManager;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class ExtAppMainWindow extends AbstractMainWindow {

    private InfoWindow openedCarTooltip = null;

    @Inject
    private SerialChart redLineChart;

    @Inject
    private SerialChart greenSerialChart;

    @Inject
    private SerialChart blueSerialChart;

    @Inject
    private SerialChart greenLineChart;

    @Inject
    private PieChart pieChart;

    @Inject
    private Table<Route> routesTable;

    @Inject
    private ComponentsFactory componentsFactory;

    @Inject
    private MapViewer map;

    @Inject
    private SideMenu sideMenu;

    @Inject
    private CollectionDatasource<Route, UUID> routeDs;

    @Override
    public void init(Map<String, Object> params) {
        setChartData();
        setPieChartData();
        initMap();
        initTable();
        initSideMenu();
    }

    private void initSideMenu() {
        SideMenu.MenuItem dashboardMenuItem = sideMenu.createMenuItem("dashboard",
                getMessage("dashboard"),
                "icons/dashboard.png",
                menuItemAction -> buttonNotImplementedAction()
        );
        dashboardMenuItem.setStyleName("menubutton-selected");
        sideMenu.addMenuItem(dashboardMenuItem);

        sideMenu.addMenuItem(
                sideMenu.createMenuItem("cars",
                        getMessage("cars"),
                        "icons/car.png",
                        menuItemAction -> buttonNotImplementedAction())
        );
        sideMenu.addMenuItem(
                sideMenu.createMenuItem("orders",
                        getMessage("orders"),
                        "icons/list.png",
                        menuItemAction -> buttonNotImplementedAction())
        );
        sideMenu.addMenuItem(
                sideMenu.createMenuItem("reports",
                        getMessage("reports"),
                        "icons/report.png",
                        menuItemAction -> buttonNotImplementedAction())
        );
        sideMenu.addMenuItem(
                sideMenu.createMenuItem("settings",
                        getMessage("settings"),
                        "icons/settings.png",
                        menuItemAction -> buttonNotImplementedAction())
        );
    }

    private void initTable() {
        routeDs.addItem(new Route("00:11", "149 S 4th St", "202 S 14th St"));
        routeDs.addItem(new Route("00:32", "1192 Fremont St", "1252 Randol Ave"));
        routeDs.addItem(new Route("00:46", "760 Chapman St", "905 Morse St"));
        routeDs.addItem(new Route("01:03", "986 Silicon Dr", "1042 Newhall St"));
        routeDs.addItem(new Route("01:52", "1080 Portola Ave", "790 Locust St"));
        routeDs.addItem(new Route("02:27", "1098 Lexington St", "1313 Franklin St"));

        routesTable.addGeneratedColumn("direction", routes -> {
            Label dataTypeLabel = componentsFactory.createComponent(Label.class);
            dataTypeLabel.setValue("\u2192");
            dataTypeLabel.setStyleName("direction");
            return dataTypeLabel;
        });

        routesTable.setSettingsEnabled(false);
    }

    private void initMap() {
        GeoPoint center = map.createGeoPoint(37.3359782, -121.924535);
        map.setCenter(center);
        map.setScrollWheelEnabled(false);
        map.setDraggable(false);
        map.setZoom(14);

        Marker marker = map.createMarker("3C4PDCAB9FT553312\nPadillac Fanta Se", map.createGeoPoint(37.340380, -121.961185), true);
        MarkerImage icon = map.createMarkerImage("VAADIN/themes/halo/car/taxi_60.svg");
        marker.setIcon(icon);
        marker.setClickable(true);
        marker.setDraggable(false);
        map.addMarker(marker);

        marker = map.createMarker("1GCGK29R2XF085887\nDMW GP", map.createGeoPoint(37.334988, -121.915265), true);
        icon = map.createMarkerImage("VAADIN/themes/halo/car/taxi_90.svg");
        marker.setIcon(icon);
        marker.setClickable(true);
        marker.setDraggable(false);
        map.addMarker(marker);

        marker = map.createMarker("1FMCU4K32BKC11669\nFevrolet Fishdorado", map.createGeoPoint(37.333078, -121.961786), true);
        icon = map.createMarkerImage("VAADIN/themes/halo/car/taxi_210.svg");
        marker.setIcon(icon);
        marker.setClickable(true);
        marker.setDraggable(false);
        map.addMarker(marker);

        marker = map.createMarker("1GCHK34F6VE179779\nPulmmer P3", map.createGeoPoint(37.338332, -121.879474), true);
        icon = map.createMarkerImage("VAADIN/themes/halo/car/taxi_300.svg");
        marker.setIcon(icon);
        marker.setClickable(true);
        marker.setDraggable(false);
        map.addMarker(marker);

        marker = map.createMarker("1GCEK14T22Z280951\nPulmmer P3", map.createGeoPoint(37.330552, -121.929256), true);
        marker.setIcon(icon);
        marker.setClickable(true);
        marker.setDraggable(false);
        map.addMarker(marker);

        map.addMarkerClickListener(e -> {
            Marker clickedMarker = e.getMarker();
            String caption = clickedMarker.getCaption();
            caption = caption.replaceAll("\n", "<br>");
            InfoWindow w = map.createInfoWindow(caption, clickedMarker);
            if (this.openedCarTooltip != null) {
                map.closeInfoWindow(this.openedCarTooltip);
            }
            map.openInfoWindow(w);
            this.openedCarTooltip = w;
        });

        map.addInfoWindowClosedListener(e ->
                this.openedCarTooltip = null);
    }

    private void setChartData() {
        ListDataProvider topRedLineDataProvider = new ListDataProvider();
        ListDataProvider topGreenSerialDataProvider = new ListDataProvider();
        ListDataProvider topBlueSerialDataProvider = new ListDataProvider();
        ListDataProvider topGreenLineDataProvider = new ListDataProvider();

        int[] redLineChartData = {5, 7, 6, 9, 7, 8, 5, 6, 4, 6, 5, 7, 4, 5, 3, 4, 2, 0};
        for (int aRedLineChartData : redLineChartData) {
            topRedLineDataProvider.addItem(graphData(aRedLineChartData));
        }

        redLineChart.setDataProvider(topRedLineDataProvider);

        int[] greenSerialChartData = {
                3, 2, 5, 4, 3, 2, 1, 3, 3, 5,
                3, 3, 2, 3, 3, 5, 2, 2, 3, 1,
                5, 4, 3, 2, 1, 3, 3, 5, 3, 1};
        int[] greenSerialChartDataBack = {
                8, 8, 5, 9, 7, 9, 7, 9, 9, 6,
                7, 9, 8, 9, 3, 5, 6, 8, 7, 9,
                7, 9, 3, 6, 1, 3, 6, 5, 9, 7};
        for (int i = 0; i < greenSerialChartData.length; i++) {
            topGreenSerialDataProvider.addItem(graphDataBack(greenSerialChartData[i], greenSerialChartDataBack[i]));
        }

        greenSerialChart.setDataProvider(topGreenSerialDataProvider);

        int[] blueSerialChartData = {
                5, 4, 9, 8, 1, 9, 3, 5, 8, 8,
                5, 4, 9, 8, 1, 9, 3, 5, 3, 6,
                3, 9, 8, 9, 3, 5, 8, 2, 3, 6};
        int[] blueSerialChartDataBack = {
                8, 8, 5, 9, 7, 9, 7, 9, 9, 6,
                7, 9, 8, 9, 3, 5, 6, 8, 7, 9,
                7, 9, 3, 6, 1, 3, 6, 5, 9, 7};
        for (int i = 0; i < blueSerialChartData.length; i++) {
            topBlueSerialDataProvider.addItem(graphDataBack(blueSerialChartData[i], blueSerialChartDataBack[i]));
        }

        blueSerialChart.setDataProvider(topBlueSerialDataProvider);

        int[] greenLineChartData = {10, 8, 15, 28, 15, 18, 15, 23, 18, 12, 16, 10, 18};
        for (int aGreenLineChartData : greenLineChartData) {
            topGreenLineDataProvider.addItem(graphData(aGreenLineChartData));
        }

        greenLineChart.setDataProvider(topGreenLineDataProvider);
    }

    private void setPieChartData() {
        ListDataProvider dataProvider = new ListDataProvider();

        dataProvider.addItem(pieCount(27, getMessage("late"), "#FDD400"));
        dataProvider.addItem(pieCount(61, getMessage("inTime"), "#84B761"));
        dataProvider.addItem(pieCount(12, getMessage("early"), "#67B7DC"));

        pieChart.setDataProvider(dataProvider);
    }

    public void buttonNotImplementedAction() {
        showNotification(getMessage("buttonMessage"), NotificationType.HUMANIZED);
    }

    private MapDataItem graphData(int value) {
        MapDataItem item = new MapDataItem();
        item.add("value", value);
        return item;
    }

    private MapDataItem graphDataBack(int value, int backValue) {
        MapDataItem item = new MapDataItem();
        item.add("value", value);
        item.add("backValue", backValue);
        return item;
    }

    private MapDataItem pieCount(int carClassCount, String carClassName, String carColor) {
        MapDataItem item = new MapDataItem();
        item.add("class", carClassCount);
        item.add("type", carClassName);
        item.add("color", carColor);
        return item;
    }

    public void signOut() {
        Window window = ComponentsHelper.getWindow(this);
        if (window == null) {
            throw new IllegalStateException("Unable to find Frame for logout button");
        }

        window.saveSettings();

        final WebWindowManager wm = (WebWindowManager) window.getWindowManager();
        wm.checkModificationsAndCloseAll(() -> {
            Connection connection = wm.getApp().getConnection();
            connection.logout();
        });
    }
}