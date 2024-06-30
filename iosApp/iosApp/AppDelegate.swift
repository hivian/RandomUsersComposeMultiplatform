import Foundation
import UIKit
import GoogleMaps
import ComposeApp

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {

        KoinCommonKt.doInitKoin()

        if let apiKey = getGoogleMapApiKey() {
            GMSServices.provideAPIKey(apiKey)
        }

        return true
    }

    private func getGoogleMapApiKey() -> String? {
        guard let path = Bundle.main.path(forResource: "Secrets", ofType: "plist") else {
            print("Secrets.plist file not found.")
            return nil
        }

        guard let data = try? Data(contentsOf: URL(fileURLWithPath: path)) else {
            print("Unable to load data from Secrets.plist.")
            return nil
        }

        // Deserialize the data from the plist file using try?
        if let plistData = try? PropertyListSerialization.propertyList(from: data, options: [], format: nil),
            let dict = plistData as? [String: Any] {
            return dict["GOOGLE_MAPS_IOS_API_KEY"] as? String
        }

        print("Error reading Secrets.plist.")
        return nil
    }
}
