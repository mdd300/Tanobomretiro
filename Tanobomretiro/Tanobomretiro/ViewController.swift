//
//  ViewController.swift
//  Tanobomretiro
//
//  Created by Victor Oshiro on 26/04/17.
//  Copyright © 2017 Victor Oshiro. All rights reserved.
//

import UIKit
import MapKit
import Foundation

class ViewController: UIViewController,MKMapViewDelegate, NSObject{

    var gerenciadorLocal = CLLocationManager()
    
    @IBOutlet weak var mapa: MKMapView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        
        let localizacao: CLLocationCoordinate2D = CLLocationCoordinate2DMake(-23.525108, -46.641265)
        let casa: CLLocationCoordinate2D = CLLocationCoordinate2DMake(-23.564093, -46.532219)
        let areaVisualizacao: MKCoordinateSpan = MKCoordinateSpanMake(0.04, 0.04)
        let regiao: MKCoordinateRegion = MKCoordinateRegionMake(localizacao, areaVisualizacao)
        
        mapa.setRegion( regiao , animated: true)
        
        let anotacao = MKPointAnnotation()
        

        //configurar
        anotacao.coordinate = localizacao
        anotacao.title = "Teste"
        anotacao.subtitle = "teste"
        
        var url: NSURL = NSURL(string: "xperiencebr.com")!
        var request:NSMutableURLRequest = NSMutableURLRequest(url:url as URL)

        mapa.addAnnotation(anotacao)
        
        
        
    }
    
    func itemsDownloaded(items: NSArray){
}


class HomeModel: NSObject, URLSessionDataDelegate {
    
    //properties
    
    weak var delegate: HomeModelProtocal!
    
    var data : NSMutableData = NSMutableData()
    
    let urlPath: String = "http://xperiencebr.com/conexao.php" //this will be changed to the path where service.php lives
    
    
    func downloadItems() {
        
        let url: NSURL = NSURL(string: urlPath)!
        var session: NSURLSession!
        let configuration = NSURLSessionConfiguration.defaultSessionConfiguration()
        
        
        session = NSURLSession(configuration: configuration, delegate: self, delegateQueue: nil)
        
        let task = session.dataTaskWithURL(url)
        
        task.resume()
        
    }
    
    func URLSession(session: NSURLSession, dataTask: NSURLSessionDataTask, didReceiveData data: NSData) {
        self.data.appendData(data);
        
    }
    
    func URLSession(session: NSURLSession, task: NSURLSessionTask, didCompleteWithError error: NSError?) {
        if error != nil {
            print("Failed to download data")
        }else {
            print("Data downloaded")
            self.parseJSON()
        }
        
    }
}

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

