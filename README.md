# addressSelectorLibrary

一个 Android 版京东手机客户端（当前版本V1.15）风格的级联地址选择器。

![image](https://github.com/Hunter2916/addressSelectorLibrary/blob/master/20190513205631.jpg)

## 添加依赖

项目的 `build.gradle` 中：

    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io"}
        }
    }
    
模块的 `build.gradle` 中：

    dependencies {
        ...
        implementation 'com.github.Hunter2916:addressSelectorLibrary:v1.1.5'
    }
    
## 使用方法

### 使用原始视图

    AddressSelector selector = new AddressSelector(context);
    selector.setOnAddressSelectedListener(new AddressSelector.OnAddressSelectedListener() {
        @Override
        public void onAddressSelected(Province province, City city, County county, Street street) {
            // blahblahblah
        }
    });
            
    View view = selector.getView();
    // frameLayout.addView(view)
    // new AlertDialog.Builder(context).setView(view).show()
    // ...
    
### BottomDialog

    BottomDialog dialog = new BottomDialog(context);
    dialog.setOnAddressSelectedListener(listener);
    dialog.show();
    
### 使用自定义数据源

    selector.setAddressProvider(new AddressProvider() {
        @Override
        public void provideProvinces(AddressReceiver<Province> addressReceiver) {
            List<Province> provinces = addressApi.provincesFromDatabase();
            addressReceiver.send(provinces);    
        }
    
        @Override
        public void provideCitiesWith(int provinceId, AddressReceiver<City> addressReceiver) {
            new Thread(() -> {
                List<City> cities = addressApi.citiesSync(provinceId);
                addressReceiver.send(cities);
            }).start();
        }
    
        @Override
        public void provideCountiesWith(int cityId, AddressReceiver<County> addressReceiver) {
            addressApi.counties(cityId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        counties -> addressReceiver.send(counties),
                        throwable -> addressReceiver.send(null)
                    );
        }
    
        @Override
        public void provideStreetsWith(int countyId, AddressReceiver<Street> addressReceiver) {
            // blahblahblah 
        }
    });
    
## 关于我

**Chihane Habana**

- <https://blog.csdn.net/Hunter2916>

## 许可证

[MIT License](http://chihane.in/license)
很感谢chihane的：https://github.com/chihane/JDAddressSelector
