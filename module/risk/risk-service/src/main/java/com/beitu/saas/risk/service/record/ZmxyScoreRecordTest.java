package com.beitu.saas.risk.service.record;

/**
 * Created by liupengpeng on 2017/11/24.
 */

public class ZmxyScoreRecordTest {
    private static String serverUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";
    private static String charset = "UTF-8";
    private static String appId = "1000923";
    private static String key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANXZBkbEWJ2qH6xKno5QLuaR364xUYz1KPxfQqrKD+N26gI4BUuNAglD2Vy34Z7ovl16DrkyF5jde2j5EkdTtG5KCOsRQXO5luwjTbSEhzzWV+m6Fgv3tRE6lnv0QV+mViwTMSGwIbXuwQ9gy5esO3GuRx1HvjpsNJqNxT/haANtAgMBAAECgYBeh4LYwW6Ss2Mw3ANe0V8KGS2RaMet0al+dfWYXZ3MZQJoXK8qiHh/8/gfnkEj56pO0+eyQcxgjHdDHebQlNXKpAx/TzakrY2soAgLdFVZYArG2sCJ54IJM30p/UnZneDnzB5OUv4bT7xlUU0743OMnW8odDFRw6V2IJQcngbiGQJBAPBofqefEaAwYtz8Gl1SlSY20ge50Yqs/G8fd/GpqvPwgQafXkJfSnwFM+tspgm2a/LJ3LznDqIWIDCls5ciUFMCQQDjt4vJG1LYZPq5IySoG9gVU31FLdRhA58KbqSfW4mTdsdn7C1IAVEduiI1PygQIqa/HUTBHiFfD2MI0Zj19OU/AkA26CbFMddctrBduFZtKgdWiv69NPteqNOerZk/YHji7fMKCCwHDKx+VtHc2xuw7DELan83xa3Z5yPC+JVj1zpxAkBhJbHN9wkt5k6MYzDFQzO5TWf49mdPiBL2iWNT1OJpddf2PuzGdkFb1Ee5vZri31WR3POzC56jBlZysbB25aZTAkA416ypqfu+DcUB0SLosl0vds07NNo33BQc3Bhj2ERyUR/7TiOnmfoQrKTv7R5GkwgiN0HKw+17PK6sMDE9mMpX";
    private static String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDy/GQxk2cDqC4mUC5zhaNXwms3Oc0/tfg1mespIQEPyYslq47XgHrb1BW34cmuu5f+M5z1yPiWA5+RV89d66WC3hoEjFXIBi7bcaG9xdnAdQMRz5JWfubBRn+CqtuCjxORv/3oCVYl9XJuKI15WzeTZ3LJExa/2qPaYGNSQ/ZIQIDAQAB";


//    public static void main(String[] args) throws Exception {
//        //Hbase初始化配置
//        if (!System.getProperties().containsKey("HADOOP_USER_NAME")) {
//            System.getProperties().setProperty("HADOOP_USER_NAME", "risk");
//        }
//
//        Configuration configuration = HBaseConfiguration.create();
//        ClassLoader loader = InitHbase.class.getClassLoader();
//        URL resource = loader.getResource("hbase-site.xml");
//        if (resource != null) {
//            configuration.addResource(resource);
//        }
//        configuration.setStrings("hbase.zookeeper.quorum", "node-01,node-02,node-03");
//        if (!configuration.onlyKeyExists(HConstants.HBASE_CLIENT_SCANNER_TIMEOUT_PERIOD)) {
//            configuration.setLong(HConstants.HBASE_CLIENT_SCANNER_TIMEOUT_PERIOD, 1200000L);
//        }
//        HBaseHelper.setConfiguration(configuration);
//
//        //ES初始化配置
//        Settings.Builder builder = Settings.builder();
//        assert StringUtils.isEmpty("lake");
//        builder.put("cluster.name", "lake");
//        builder.put("client.transport.sniff", true);
//        Settings settings = builder.build();
//        ESHelper.setSettings(settings);
//
//        List<InetSocketTransportAddress> list = new ArrayList<>();
//        String[] hosts = "node-01,node-02,node-03".split(",");
//        int defaultPort = 9300;
//        for (String host : hosts) {
//            InetSocketTransportAddress address;
//            if (host.contains(":")) {
//                address = new InetSocketTransportAddress(InetAddress.getByName(host.split(":", 2)[0]), Integer.valueOf(host.split(":", 2)[1]));
//            } else {
//                address = new InetSocketTransportAddress(InetAddress.getByName(host), defaultPort);
//            }
//            list.add(address);
//        }
//        ESHelper.setAddresses(list.toArray(new InetSocketTransportAddress[]{}));
//
//
//        UUID uuid = UUID.randomUUID();
//        System.out.println(uuid.toString());
//        Long startTime = new Date().getTime();
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//
//        TripleServiceTypeEnum tripleServiceTypeEnum = TripleServiceTypeEnum.ZMXY_SCORE;
//
//        TripleZmxyScoreOutput zmxyScoreResult = new TripleZmxyScoreOutput();
//        zmxyScoreResult.setOpenId("268809117269370834259123959");
//        ZhimaCreditScoreGetRequest creditScoreGetRequest = new ZhimaCreditScoreGetRequest();
//        creditScoreGetRequest.setPlatform("zmop");
//        creditScoreGetRequest.setChannel("api");
//        //transactionId，该标记是商户每次请求的唯一标识。建议使用uuid进行传递，
//        creditScoreGetRequest.setTransactionId(UUID.randomUUID().toString());
//        creditScoreGetRequest.setProductCode("w1010100100000000001");
//        creditScoreGetRequest.setOpenId("268809117269370834259123959");
//        DefaultZhimaClient client = new DefaultZhimaClient(serverUrl,
//                appId, charset, key,
//                pubKey);
//        ZhimaCreditScoreGetResponse creditScoreGetResponse = client.execute(creditScoreGetRequest);
//        String scoreJson = creditScoreGetResponse.getBody();
//        ZmxyScore zmxyScore = JSONUtils.json2pojo(scoreJson, ZmxyScore.class);
//        if ("true".equals(zmxyScore.getSuccess())) {
//            zmxyScoreResult.setZmxyStatus(TripleStatusEnum.success.getValue());
//            zmxyScoreResult.setZmScore(Integer.valueOf(zmxyScore.getZmScore()));
//            zmxyScoreResult.setZmScoreBizNo(zmxyScore.getBizNo());
//        } else {
//            zmxyScoreResult.setZmxyStatus(TripleStatusEnum.zmxy_score_fail.getValue());
//        }
//        String json = JSONUtils.obj2json(zmxyScoreResult);
//
//
//        try {
//            Connection connection = HBaseHelper.connect();
//            TripleServiceRecord record = new TripleServiceRecord(HBaseHelper.toBytes(uuid), connection);
//            record.setData(json);
//            record.setType(tripleServiceTypeEnum);
//            System.out.println(record.save());
//
//            String uuid1 = uuid.toString();
//
//            TripleServiceBaseOutput result = new TripleRecordService().queryDataRecord(uuid1);
//            System.out.println(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
//
//            TripleServiceRecord record1 = new TripleServiceRecord(HBaseHelper.toBytes(uuid1), connection);
//            record1.load();
//            System.out.println(record1.toString());
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//
//        Client client1 = ESHelper.connect();
//        String index = "risk-triple-service-call-record." + format.format(startTime);
//        TripleServiceCallRecord tripleServiceCallRecord = new TripleServiceCallRecord(index, "record", uuid.toString(), client1);
//        tripleServiceCallRecord.setCall(new TripleServiceCallRecord.Call(true, (int) (new Date().getTime()-startTime), "ok"))
//                .setUser(new TripleServiceCallRecord.User("钟文浩", "15079084006", "360502199610050033"))
//                .setTriple(TripleServiceTypeEnum.SHUMEI_BLACK)
//                .setProduct(ProductTypeEnum.SDXL)
//                .setCreatedAt(new Date());
//        System.out.println(tripleServiceCallRecord.insert());
//
//        TripleServiceCallRecord tripleServiceCallRecord1 = new TripleServiceCallRecord(index, "record", uuid.toString(), client1);
//        tripleServiceCallRecord1.select();
//        System.out.println(tripleServiceCallRecord1.toString());
//    }

//    public static void main(String[] args) {
//        String json = readTxt("/Users/zzzz76/Desktop/pcas-code.json");
//        StringBuilder areaBuilder = new StringBuilder();
//        StringBuilder streetBuilder = new StringBuilder();
//        long count = 1L;
//        long countStreet = 1L;
//        List<AddressProvince> addressProvinces = JSON.parseArray(json, AddressProvince.class);
//        addressProvinces.sort(ASC_Province_COMPARATOR);
//        for (AddressProvince addressProvince: addressProvinces) {
//            String provinceCode = addressProvince.getCode();
//            String provinceName = addressProvince.getName();
//            //为了保证信息的可查性，每一个省 城 区 都得补全入库，比如台湾省
//            List<AddressCity> addressCities = new ArrayList<>();
//            AddressCity zeroAddressCity = new AddressCity()
//                    .setCode(provinceCode + "00")
//                    .setName(provinceName);
//            addressCities.add(zeroAddressCity);
//            if (CollectionUtils.isNotEmpty(addressProvince.getChilds())) {
//                addressCities.addAll(addressProvince.getChilds());
//                addressCities.sort(ASC_City_COMPARATOR);
//            }
//            for (AddressCity addressCity: addressCities) {
//                String cityCode = addressCity.getCode();
//                String cityName = addressCity.getName();
//
//                List<AddressArea> addressAreas = new ArrayList<>();
//                AddressArea zeroAddressArea = new AddressArea()
//                        .setCode(cityCode + "00")
//                        .setName(cityName);
//                addressAreas.add(zeroAddressArea);
//                if (CollectionUtils.isNotEmpty(addressCity.getChilds())) {
//                    addressAreas.addAll(addressCity.getChilds());
//                    addressAreas.sort(ASC_Area_COMPARATOR);
//                }
//                for (AddressArea addressArea: addressAreas) {
//                    String areaCode = addressArea.getCode();
//                    String areaName = addressArea.getName();
//                    //地区入库
//                    AddressAreaEntity addressAreaEntity = new AddressAreaEntity()
//                            .setId(count++)
//                            .setProvinceCode(provinceCode + "0000000")
//                            .setProvinceName(provinceName)
//                            .setCityCode(cityCode + "00000")
//                            .setCityName(cityName)
//                            .setAreaCode(areaCode + "000")
//                            .setAreaName(areaName);
//                    areaBuilder.append(JSON.toJSONString(addressAreaEntity) + ",");
//
//                    List<AddressStreet> addressStreets = new ArrayList<>();
//                    AddressStreet zeroAddressStreet = new AddressStreet()
//                            .setCode(areaCode + "000")
//                            .setName(areaName);
//                    addressStreets.add(zeroAddressStreet);
//                    if (CollectionUtils.isNotEmpty(addressArea.getChilds())) {
//                        addressStreets.addAll(addressArea.getChilds());
//                        addressStreets.sort(ASC_Street_COMPARATOR);
//                    }
//                    for (AddressStreet addressStreet: addressStreets) {
//                        String streetCode = addressStreet.getCode();
//                        String streetName = addressStreet.getName();
//                        //乡镇入库
//                        AddressStreetEntity addressStreetEntity = new AddressStreetEntity()
//                                .setId(countStreet++)
//                                .setAreaCode(areaCode + "000")
//                                .setStreetCode(streetCode)
//                                .setStreetName(streetName);
//                        streetBuilder.append(JSON.toJSONString(addressStreetEntity) + ",");
//                    }
//                }
//            }
//        }
//        writeTxt("/Users/zzzz76/Desktop/areaBuilder.json", areaBuilder.toString());
//        writeTxt("/Users/zzzz76/Desktop/streetBuilder.json", streetBuilder.toString());
//    }

//    public static String readTxt(String filePath) {
//        String rtn = null;
//        File txtFile = new File(filePath);
//        if (!txtFile.exists()) {
//            return rtn;
//        }
//
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(txtFile));
//            String s = "";
//            StringBuffer sb = new StringBuffer("");
//            while ((s = br.readLine()) != null) {
//                String[] xx = s.split(" ");
//                for (int i = 0; i < xx.length; i++) {
//                    sb.append(xx[i]);
//                }
//            }
//            br.close();
//            return sb.toString();
//        } catch (IOException e) {
//            e.getStackTrace();
//        }
//        return rtn;
//    }
//
//    public static void writeTxt(String filePath, String txt) {
//        try {
//            PrintWriter pw = new PrintWriter(new FileWriter(filePath, true),
//                    true);
//            pw.println(txt);
//            pw.close();
//        } catch (Exception e) {
//            System.out.println("Exception: " + e);
//        }
//    }
//
//    /**
//     * 省份对象降序排序器
//     */
//    public static final Comparator<AddressProvince> ASC_Province_COMPARATOR = new AscProvinceComparator();
//
//    private static class AscProvinceComparator implements Comparator<AddressProvince> {
//        @Override
//        public int compare(AddressProvince key1, AddressProvince key2) {
//            return key1.getCode().compareTo(key2.getCode());
//        }
//    }
//
//    /**
//     * 城市对象降序排序器
//     */
//    public static final Comparator<AddressCity> ASC_City_COMPARATOR = new AscCityComparator();
//
//    private static class AscCityComparator implements Comparator<AddressCity> {
//        @Override
//        public int compare(AddressCity key1, AddressCity key2) {
//            return key1.getCode().compareTo(key2.getCode());
//        }
//    }
//
//    /**
//     * 区县对象降序排序器
//     */
//    public static final Comparator<AddressArea> ASC_Area_COMPARATOR = new AscAreaComparator();
//
//    private static class AscAreaComparator implements Comparator<AddressArea> {
//        @Override
//        public int compare(AddressArea key1, AddressArea key2) {
//            return key1.getCode().compareTo(key2.getCode());
//        }
//    }
//
//    /**
//     * 乡镇对象降序排序器
//     */
//    public static final Comparator<AddressStreet> ASC_Street_COMPARATOR = new AscStreetComparator();
//
//    private static class AscStreetComparator implements Comparator<AddressStreet> {
//        @Override
//        public int compare(AddressStreet key1, AddressStreet key2) {
//            return key1.getCode().compareTo(key2.getCode());
//        }
//    }
}
